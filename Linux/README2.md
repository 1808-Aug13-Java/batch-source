# Definitions
  - Unix - original operating system made by AT&T, now refers to family of operating systems conforming to POSIX 
  - Linux - kernel used by GNU, created by Linus Torvalds as an open source version of UNIX
  - GNU/Linux - Operating system that uses Linux. Created by GNU organization (GNU ain't UNIX)
  - Shell - Interface between user and operating system
  - Console - don't know
  - Terminal - Nowadays, a terminal emulator, a gui program that sits on the X-window layer and mimics the terminal
  - bash - implementation of the shell

## Navigating
 - `cd -` *to switch paths*
 - `!!` *to repeat previous command*
 - use *arrow keys* to browse command history 
 - use *tab completion* 
 - use *wildcards* 
 - use *backticks* to interpret nested commands 
     - see *Working With Files*
 - use *alias* shortcut commands 
## ls
 - `ls --sort=size`   **sort by size**
 - `ls -lah` **print everything**
 - `ls -R` **recurse and print**
 - `ls -d */` **print only directory names**
## cat
 - `cat *sql` **concatenate all sql files in dir**
     - can't pipe in arguments
 - `find . -type f -iname "*.java" -exec cat {} \; | wc -l` **get total number of lines of all java files in project**

## tail
 - `tail -f -n 200 file` **live monitor changes to file up to the last 200 lines**
## Working With Files 
## mkdir/rmdir
 - mkdir -p dir1/dir2/dir3/dir4 
## tar
 - `tar xf somefile.tar` *extract*
 - `tar xzf somefile.tar.gz` decompress and extract*
 - `tar czf somefile.tar.gz somefile/*` *compress and archive all files in somefile*
 - `unzip somefile.zip -d zips` *decompress and extract files in zip into zips*
 - wget 
 - pipes
 - redirects

## Security
## chmod 
 - `chmod u+x` *give owner executable permission*
 - `chmod g+rw o-rwx` *give group read+write, remove all permissions to everybody else*
##
 - `chown` `chgrp`
 - stickybit, setUID
## Finding Things
## locate
 - search very quickly
## grep
     `grep -nR dev -e "SpringBootApplication"` *recurse and search through all files in dev whose contents match "SpringBootApplication"*

## Other things
## setting environment variables
 `set PATH=$PATH:/opt`
 `export PATH`
## ps -aux / ps aux
   - `ps aux | grep "spring-boot"` *find all processes running spring-boot*
   - `pgrep -f "spring-boot"` *get pids of all running spring-boot applications*
   - `pkill -f "spring-boot"` *kill all running spring-boot applications*
## running things in the background
 - `mvn spring-boot:run &> logs/log.out &`
   - `CTRL+C` to interrupt
## getting help



