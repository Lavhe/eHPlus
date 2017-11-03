// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})


//this shit!
(function () {
 var config = {
    apiKey: "AIzaSyBo4IldpfWD70S8Nbyht6RlVGEWZiRdXKA",
    authDomain: "ekhack-511f3.firebaseapp.com",
    databaseURL: "https://ekhack-511f3.firebaseio.com",
    projectId: "ekhack-511f3",
    storageBucket: "ekhack-511f3.appspot.com",
    messagingSenderId: "440015837719"
  };
	
	//all logic here
	 //check if user is logged in
     var promise = firebase.auth().onAuthStateChanged(function (user) {
		  if (!user) {
        //not loggedin
                        $(location).attr('href', 'login.php');
                    }
					else
					{
						//user is logged in
					}
	 });
	
});//end of function
	
