#How to bsversioncontrol ur way thru working with Bitbucket:

Get an account and make sure you have access to repository (admin status).

Get git bash from: http://git-scm.com/downloads

#Getting the repository for the first time:
Windows: Open Git Bash// Mac: just go to terminal

 Create a directory that you want to store all future git stuff in and go there
 e.g,
 >mkdir git

 >cd git

run:
>git init

run:
>git clone [url]

...where [url] can be found on https://bitbucket.org/jgpark2/brownepoints/overview on the right sidebar with HTTPS

you should now have a folder called "brownepoitns" with all our project stuff in it.

#Making Changes:
Just in case, before you start making changes, make sure you have the latest copy.

run:
>git pull

Now you can make changes safely.

#to commit:

1. run:
>git add [filename]

if you want to see which file's you've modified so far, run: >git status
If you are editing through a IDE (by importing the project), the IDE will create its own stuff in that directory- so make sure you don't add and commit those onto the repository.

Otherwise, run:
>git add .

to add all modified files.

2. run:
>git commit -m "[comment goes here]"

Please comment well and in detail since we're using version control w/ many users. Otherwise, when something fucks up, it's really hard to know wth happened.

3. run:
>git push

This will finally upload your copies to our repository online.

#Note on merge collisions:
If you get some error with merge when you try to commit, it means someone edited and pushed the same file while you were working on your stuff. It SHOULD show you parts that's been added/modified/deleted if you reopen the file in question.
Manually merge it, and recommit.

Otherwise, you can always make sure no one else has made changes while you were working on your stuff.
If someone made changes, you want to temporarily save the changes you've made somewhere else (e.g: copy all and paste it into a notepad on the side), then update your copy to the one in the repo:
run:
>git pull

If the edited files weren't the ones you were editing, re-open up the outdated version and paste in the whole new copy thing back.
If the edited files were the ones you were editing, you'll have to manually change the code again...

