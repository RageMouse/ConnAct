<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Profile Page</h1>
        <CreateProfile v-if="showProfileForm"/>
        <MyProfile/>
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
    this.$store.dispatch('loadSkills')
    this.$store.dispatch('loadInterests')
    this.$store.dispatch("loadProfile");
  },
  data: () => ({
     showProfile: false,
     showProfileForm: true,
  }),
  methods: {
    toggleForms(){
      this.showProfile = !this.showProfile
      this.showProfileForm = !this.showProfileForm
    },
  }
}
</script>