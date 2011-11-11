#
# set properties
# Author Wolfgang Fahl
#
for f in `find . -name "*.java"`
do
  grep "\$Id" $f | grep -v java
  if [ $? -eq 0  ]
  then
    echo $f
    svn propset svn:keywords Id $f
  fi
done