#!/usr/bin/env bash

git add .
git commit -m "自动提交:$1"
git push origin master
