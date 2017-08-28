# !/usr/bin/python

import os, sys, time

print "the dir is: %s" %os.listdir(os.getcwd())
time.sleep(1)
avi = ".avi"
for f in os.listdir(os.getcwd()):
    #print(f[:-4])
    time.sleep(1)
    #tmp = file[:- ]
    if ".flv" in f:
        os.system('ffmpeg %s -i "%s" "%s%s"' % (f, avi, f[:-4], ".flv"))
        #ffmpeg -i "test - Copy (1).flv" -f avi -vcodec mpeg4 -acodec libmp3lame out1.avi
    #    os.rename(file, "1_" + tmp)
    #if "09_" in file:
    #    os.rename(file, "2_" + tmp)
    #if "08_" in file:
    #    os.rename(file, "3_" + tmp)
    #if "07_" in file:
    #    os.rename(file, "4_" + tmp)

print "Successfully renamed."
time.sleep(5.5)

# listing directories after renaming "tutorialsdir"
