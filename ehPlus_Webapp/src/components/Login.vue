<template>
<div class="hello">
  <h1>{{ msg }}</h1>
  <h2>Log in</h2>
  <a href="#" role="button" v-on:click="login">Login</a>
</div>
</template>

<script>
import firebase from 'firebase'

export default {
  name: 'Login',
  data() {
    return {
      msg: 'You are logged in'
    }
  },
  methods:{
    login(){
      firebase.auth().signInWithEmailAndPassword("mulavhe@gmail.com","123456").then(
        function(user){
            console.log(user.email);
        },function(err){
            alert(err);
        }
      );
    },
  },
  mounted() {
    //do something after mounting vue instance
    var self = this;
    firebase.auth().onAuthStateChanged(
      function(user) {
      if (user) {
        firebase.database().ref('/User/' + user.uid + "/role").once('value').then(function(role) {
          if(role.val() == 1){
            // Medical practitioner
            self.$router.push({ name: 'MedicalprofessionalDashboard', params: { userId: user.uid }})
          }else if(role.val() == 2){
            // Admin
            self.$router.push({ name: 'MedicalAdminDashboard', params: { userId: user.uid }})
          }
        });
      } else {

      }
       });
    }
  }
</script>
