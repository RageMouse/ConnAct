<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Profile Page</h1>
        <CreateProfile v-if="showProfileForm"/>
        <MyProfile v-if="showProfile"/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import CreateProfile from '@/components/CreateProfile.vue'
import MyProfile from '@/components/MyProfile.vue'

export default {
  name: 'Home',
  components: {
    CreateProfile,
    MyProfile
  },
  mounted(){
    this.axios
      .get("http://192.168.178.20:8089/profile/")
      .then((response) => {
          console.log(response.status);
          if (response.status == 200) {
            this.showProfile = true
            this.showProfileForm = false
          }
        }
      );
  },
  data: () => ({
     showProfile: false,
     showProfileForm: true,
  }),
  methods: {
    hideForm(){
      this.showProfile = true
      this.showProfileForm = false
    },
  }
}
</script>