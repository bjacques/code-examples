
# http://www.cyberciti.biz/tips/howto-linux-unix-bash-shell-setup-prompt.html
export PS1="\w> "

# http://askubuntu.com/questions/466198/how-do-i-change-the-color-for-directories-with-ls-in-the-console
export LS_COLORS='di=33:fi=0:ln=95:pi=5:so=5:bd=5:cd=5:or=37:mi=0:ex=1;33:*.rpm=36'

export GREP_COLOR='00;38;5;226'


alias ll='ls -l --color=auto'
alias la='ll -a'
alias lr='la -tr'
alias grepc='grep --color=auto'
alias dev="echo 'dev-password' | clip"

alias vi=vim

alias gs='git status'
alias ga='git add'
alias gd='git diff'
alias gdc='git diff --cached'
alias grh='git reset HEAD'
alias gcm='git commit -m'
alias gco='git checkout'
alias gcom='git checkout master'
alias gps='git push'
alias gpl='git pull'
alias gmm='git merge master'
alias gdtool="git difftool --no-prompt"

# git history
alias gh='git hist'
alias gl='git log --stat --pretty=short --graph'
# see changes in a commit -> git show 1c99c3e
# see deleted files
alias gld="git log --diff-filter=D --summary"
# recover files -> gco $commit~1 <filename>
# revert a commit -> git revert 0ad5a7a6

# discard all uncommitted files in working directory
# git clean -fd
# git reset --hard

# rewrite previous commit message
alias gcma="git commit --amend"

# rewrite/squash previous commit messgaes if you haven't already pushed
# git rebase -i HEAD~2

alias=avro-tools='java -jar /c/tools/avro-tools.jar'
alias=avro-tools-fromjson='java -jar /c/tools/avro-tools.jar fromjson --schema-file '
alias jq='/c/tools/jq-win64.exe'
