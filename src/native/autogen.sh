#export WANT_AUTOCONF_2_5=1
#WANT_AUTOMAKE_1_5=1

#autoheader linux
libtoolize --force
aclocal
automake --foreign --include-deps --add-missing --copy
autoconf
