
# http://www.cyberciti.biz/tips/howto-linux-unix-bash-shell-setup-prompt.html
export PS1="\w> "

# http://askubuntu.com/questions/466198/how-do-i-change-the-color-for-directories-with-ls-in-the-console
export LS_COLORS='di=33:fi=0:ln=95:pi=5:so=5:bd=5:cd=5:or=37:mi=0:ex=1;33:*.rpm=36'

alias ll='ls -l --color=auto'
alias la='ll -a'
alias lr='la -tr'

alias vi=vim

alias gs='git status'
alias ga='git add'
alias gd='git diff'
alias gcm='git commit -m'
alias gco='git checkout'
alias gcom='git checkout master'
alias gps='git push'
alias gpl='git pull'
alias gmm='git merge master'

