package com.brad.license;

import org.junit.Assert;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;

import com.brad.domain.Applicant;

public class Main {

	public static void main(String[] args) {

		Main m = new Main();
		m.start();
	}

	private void start() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		kbuilder.add(ResourceFactory.newClassPathResource(
				"licenseApplication.drl", getClass()),

		ResourceType.DRL);

		if (kbuilder.hasErrors()) {

			System.err.println(kbuilder.getErrors().toString());

		}

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		StatelessKnowledgeSession ksession = kbase
				.newStatelessKnowledgeSession();

		Applicant applicant = new Applicant("Mr John Smith", 16);

		Assert.assertTrue(applicant.isValid());

		ksession.execute(applicant);

		Assert.assertFalse(applicant.isValid());
	}
}
