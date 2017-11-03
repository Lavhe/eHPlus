// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueFire from 'vuefire';
import firebase from 'firebase';

Vue.use(VueFire);

Vue.config.productionTip = false

let config = {
  apiKey: "AIzaSyBo4IldpfWD70S8Nbyht6RlVGEWZiRdXKA",
  authDomain: "ekhack-511f3.firebaseapp.com",
  databaseURL: "https://ekhack-511f3.firebaseio.com",
  projectId: "ekhack-511f3",
  storageBucket: "ekhack-511f3.appspot.com",
  messagingSenderId: "440015837719"
};
firebase.initializeApp(config);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  firebase: {
    // can bind to either a direct Firebase reference or a query
  }
})
