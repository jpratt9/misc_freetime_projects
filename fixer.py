# !/usr/bin/python

import os, sys

#print "The dir is: %s"%os.listdir(mypath)

for file in os.listdir(os.getcwd()):
    #print(file)
    tmp = file[:-4]
    if ".flv" in file:
        
    #    os.rename(file, "1_" + tmp)
    #if "09_" in file:
    #    os.rename(file, "2_" + tmp)
    #if "08_" in file:
    #    os.rename(file, "3_" + tmp)
    #if "07_" in file:
    #    os.rename(file, "4_" + tmp)

print "Successfully renamed."

# listing directories after renaming "tutorialsdir"
#print "the dir is: %s" %os.listdir(os.getcwd())\