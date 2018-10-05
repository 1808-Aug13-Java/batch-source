# Definitions
  - Unix - original operating system made by AT&T, now refers to family of operating systems conforming to POSIX 
  - Linux - kernel used by GNU, created by Linus Torvalds as an open source version of UNIX
  - GNU/Linux - Operating system that uses Linux. Created by GNU organization (GNU ain't UNIX)
  - Shell - Interface between user and operating system
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
      - the true power of cat comes here. If I have many, separate scripts for ddl and dml statements, I can `cat` them into one master file, rather than have to execute each script multiple times
 - `find . -type f -iname "*.java" -exec cat {} \; | wc -l` **get total number of lines of all java files in project**

## tail
 - `tail -f -n 200 file` **live monitor changes to file up to the last 200 lines**
     - Very useful for viewing logs during development session. Spring already outputs to the console, but this has the advantage of having multiple log files that you can view on their own screens. 
## Working With Files 
## mkdir/rmdir
 - `mkdir -p dir1/dir2/dir3/dir4` 
## tar
 - `tar xf somefile.tar` *extract*
 - `tar xzf somefile.tar.gz` decompress and extract*
     - the z option extracts a .gz file. This option is actually not necessary as tar will do it automatically
 - `tar czf somefile.tar.gz somefile/*` *compress and archive all files in somefile*
 - `unzip somefile.zip -d zips` *decompress and extract files in zip into zips*
     - if the contents of the zip file are not already inside a directory, unzipping without `-d` may have the effect of polluting the current directory with the files you are extracting
 - wget 
 - pipes
 - redirects

## Security
## chmod 
 - `chmod u+x` *give owner executable permission*
 - `chmod g+rw o-rwx` *give group read+write, remove all permissions to everybody else*
     - a good way to mess up your system is to `chmod -R 777 on /usr`
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
  - the line above assigns the value of the PATH variable to its current value + ":/opt"
  - when you run a program in bash by its unqualified name (without its absolute or relative path information), it looks in the `$Path` for that program. Included here by default is `/usr/bin` and `bin`, among others. The variable itself is a string of colon delimited paths names. 
  - the `$` is used to dereference a variable when it is part of the rvalue (right side of the statement)
 - `export PATH`
  - put these lines in .bashrc then reboot or run `exec bash` for the changes to take effect across all sessions
## ps -aux / ps aux
   - `ps aux | grep "spring-boot"` *find all processes running spring-boot*
   - `pgrep -f "spring-boot"` *get pids of all running spring-boot applications*
   - `pkill -f "spring-boot"` *kill all running spring-boot applications*
## running things in the background
 - `mvn spring-boot:run &> logs/log.out &`
 - `mvn spring-boot:run &> logs/log.out &`
   - `CTRL+C` to interrupt
## getting help
  - The following describes my approach to when I need to learn something for the first time:
      - If it's something quick or I need to hurry, I'll Google it (and make a note to come back to it later)
      - If it's a well established program that somehow I've never bothered to look into, I start with the man pages. 
      - Otherwise I'll look into its official documentation, and Google things if I need extra help
  - The amount of effort I put into this and how thoroughly I work through it depend on how much of an impact it will have on my workflow. Things like git, qutebrowser, tar, and of course all the GNU core utilities, I believe deserve thorough study for anyone serious about Linux.  


