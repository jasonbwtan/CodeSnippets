create archetype: jersey-heroku-webapp
cd {project.pom.dir}
git init
heroku create
git add *
git commit -a -m "initial commit"
git push heroku master
https://serene-springs-8078.herokuapp.com/myresource


### eclipse IDE:
http://localhost:8888/jersey-heroku-example/myresource