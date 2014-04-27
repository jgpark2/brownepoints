#USING GIT:

Bitbucket: Get an account and make sure you have access to repository (admin status).

Get git bash from: http://git-scm.com/downloads
or something similar for MACs (you guys can work it on your terminal right?)

#Fetching the repository for the first time:
Windows: Open Git Bash// Mac: just go to terminal

 Create a directory that you want to store all future git stuff in and go there (sort of like workspace folder for eclipse)
 e.g,
 >mkdir git

 >cd git

in your git folder, run:
>git init

>git clone [url]

...where [url] is https://jgpark2@bitbucket.org/jgpark2/brownepoints.git
or, it can be found on https://bitbucket.org/jgpark2/brownepoints/overview on the right sidebar with HTTPS

you should now have a folder called "brownepoitns" with all our latest project stuff in it.

#Making Changes and Implementing Your Own Feature:
You need to start working on a new feature or fix an existing code-
Before you start making ANY changes, make sure you update to the latest copy on bitbucket (called remote repository).

run:
>git pull

or:
>git pull origin master

Before starting any new implementation/fixing, create a separate branch for it:
>git checkout -b [branch-name]

where the branch name reflects the feature/fix (e.g: feature-x).
This will create the new branch and change your current branch to it.

Now make any changes you want on your local copies.

(PS: To change your active branch, "git checkout [branch-name]")
(To delete an existing branch, "git branch -d [branch-name]")

#To Commit & Push the Changes You Made:
Now that you've made plenty of changes to your local copies, you need to push it so that it shows up on the remote repo.

Check what files have been modified/added/deleted by:
>git status

To commit, first run:
>git add .

Then run:
>git commit -m "[comment goes here]"

Please comment well and in detail since we're using version control w/ many users. Otherwise, when something fucks up, it's really hard to know wth happened.

Now you can push (if you want) to our remote

run:
>git push origin [branch-name]

This will finally upload your copies to our remote repo IN your branch. Check by going online on bitbucket.

#To Push After Finishing Your New Feature / Merging:
First, change your active branch to master
>git checkout master

Update our master copy in case someone has updated the code while we were working on our feature:
>git pull origin master

Here comes the scary part.
We are going to merge the branch you've worked on into the master(active) branch.
>git merge [branch-name]

where [branch-name] is the name of the branch you've been working on.

git will try to auto-merge, but there's likely to be conflicts.
Open up files that have merge conflicts, and manually modify them.
Be extra careful (esp. of another person's code) when resolving merge conflicts! Make sure it still compiles without any bugs...

Once you've resolved the merge conflicts, and you've confirmed it works, push it (git push origin master). The merge proccess automatically adds changed files and commits it, which is why you don't need to do them again.

Unless you want to continue developing past your finished feature on the branch (which I don't recommend if you truly finished the feature), delete the branch since we don't need it anymore.
>git branch -d [branch-name]

where [branch-name] is the name of the branch you've been working on that we don't need anymore.
