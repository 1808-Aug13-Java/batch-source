#!/usr/bin/env bash
git fetch
git checkout Jerry_Kim
git merge origin/Carolyn_Rehm
echo $1
cp -r $1 ~/dev/$1
