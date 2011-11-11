#
# script for cardme release notes formatting
# WF 2009-07-27
# $Header$
#

#
#
# show usage
# 
usage() {
	echo "usage: svnlog2releasenotes [fromdate]"
	echo "  get the subversion repository notes and reformat to release notes"
	echo "	example: svnlog2releasenotes  2007-12 > recent.html"
	exit 1
}


#
# get the subversion log
#
getsvnlog() {
	svn log https://cardme.svn.sourceforge.net/svnroot/cardme > svnlog.txt
}
	
#
# reformat the subversion log to release notes format
#
reformat() {
  cat svnlog.txt  | awk  -v fromdate="$fromdate" -v todate="$todate" '
BEGIN { 
	FS="|" 
	quote="\x22"
	amp="\x26"
	ignores[i++]="comment added"
	ignores[i++]="comments fixed"
	ignores[i++]="comment improved"
	ignores[i++]="^comment$"
	ignores[i++]="^improved$"
	ignores[i++]="^keywords$"
	ignores[i++]="header"
	ignores[i++]="^keywords$"
	ignores[i++]="^removed duplicate$"
	ignores[i++]="Header added"
	ignores[i++]="Copyright year"
	ignores[i++]="copyright year"
	ignores[i++]="source formatting"
	ignores[i++]="source code layout"
	ignores[i++]="not for release notes"
}  
/^------------------------------------------------------------------------/{
	svnindex++; next  
}
/^r[0-9]+/ { 
	match($0,"^r[0-9]+")
	rev=substr($0,RSTART+1,RLENGTH-1);
	author=$2
	date=gsub(" ","",$3)
	date=substr($3,1,10)	
	if (date>=fromdate) {
	  collect=(1==1)
  }	else {
    collect=(1==0)
  }  
  if (collect) {
 		# print rev,author,date
 	}	
  next
}
{
  for (ignore in ignores) {
  	echo ignores[ignore]
  	if (match($0,ignores[ignore])) {
  		collect=(1==0)
  	}
  }		
	if (collect && length($0)>0){
	  current=$0
	  # encode html tags
	  gsub("<","\\&lt;",current);
	  gsub(">","\\&gt;",current);
	  if (text[rev]!="")
	  	text[rev]=text[rev]"<br />"
		text[rev]=text[rev]current
	}	
	next
}	
END {
  print "<ol>"
  repositorylink="http://cardme.svn.sourceforge.net/viewvc/cardme?view=rev&revision="
  baselink="http://sourceforge.net/tracker/index.php?func=detail"
  buglink    ="&group_id=99914&atid=625723"
  supportlink="&group_id=99914&atid=625724"
  patchlink  ="&group_id=99914&atid=625725"
	for (rev in text) {
	  current=text[rev]
	  # look for bug report or patch number - must have 6 digits +
	  if (match(current,"[0-9][0-9][0-9][0-9][0-9][0-9]+")) {
	    rs=RSTART
	    rl=RLENGTH
	    # get the bug or patch number
	  	linkno=substr(current,RSTART,RLENGTH)
	  	# patch or bug?
		  if (match(current,"[p|P]atch")) {
		    postfix=patchlink
		  } else if (match(current,"SR")) {
		    postfix=supportlink
		  } else {
		  	postfix=buglink
		  }
	    # replace number with link to sourceforge tracker
			link=sprintf("<a href=%s%s&aid=%s%s%s>%s</a>",quote,baselink,linkno,postfix,quote,linkno);
			current=substr(current,1,rs-1) link substr(current,rs+rl,length(current))
	  }
    printf("  <li>%s\n    (<a href=%s%s%s%s>r%s</a>)\n  </li>\n",current,quote,repositorylink,rev,quote,rev);
	}	
	print "</ol>"
}
'
}

#
# check number of command line parameters
#
if [ $# -lt 1 ]
then
  usage
fi

fromdate=$1
getsvnlog
reformat