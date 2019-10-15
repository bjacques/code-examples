import org.apache.spark.sql.functions._

/*
which partition file contains the results being returned
*/
val trade = spark.read.format("com.databricks.spark.avro").load("/path/to/data/2019120[1-5]")
(
	trade
	.where("id = 100")
	.withColumn("filename", input_file_name())
	.show(false)
)


/*
convert epoch seconds to date
*/
(
	spark.read.parquet("/path/to/data/20190101")
	.where()
	.select(to_date(from_unixtime($"timestamp_in_epoch_seconds")).as("execdate"))
	.groupBy("execdate")
	.count()
	.orderBy($"count".desc)
	.show(false)
)


/*
update an existing field named "isvalid=false" using an lookup UDF to see if the value should be "isvalid=true"
*/
val setIsValid = udf{(foo: String, bar: String, isvalid: Boolean) =>
	(foo, bar) match {
		case ("a", "x") => true
		case ("a", "y") => true
		case (_,_) => isvalid
	}
}

(
	spark.read.format("com.databricks.spark.avro").load("/path/to/data/20190101")
	.withColumn("isvalid", setOnVenue($"foo", $"bar", $"isvalid"))
	.coalesce(1)
	.write.mode("overwrite").format("com.databricks.spark.avro").save("/path/to/export")
)
