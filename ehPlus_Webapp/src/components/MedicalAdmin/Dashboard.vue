<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
import firebase from 'firebase';

export default {
  name: 'MedicalAdminDashboard',
  data () {
    return {
      msg: 'Admin'
    }
  },
  mounted() {
    //do something after mounting vue instance
    firebase.auth().signInWithEmailAndPassword("mulavhe@gmail.com","123456").then(
      function(user){
          console.log(user.email);
      },function(err){
          alert(err);
      }
    );
  }

}
</script>
