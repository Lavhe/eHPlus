<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
import firebase from 'firebase';

export default {
  name: 'MedicalprofessionalDashboard',
  data () {
    return {
      msg: 'Medical'
    }
  },
  mounted() {
    //do something after mounting vue instance
    firebase.auth().signInWithEmailAndPassword("mulavhe@gmail.com","12s3456").then(
      function(user){
          console.log(user.email);
      },function(err){
          alert(err);
      }
    );
  }

}
</script>
