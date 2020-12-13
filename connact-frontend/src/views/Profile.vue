<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3">My Profile Page</h1>
        <CreateProfile v-if="Object.keys(getProfile).length===0"/>
        <v-btn depressed color="error" @click.stop="showDeleteDialog=true">Delete</v-btn>
        <DeleteEmployee :visible="showDeleteDialog" @close="showDeleteDialog=false"/>
        <MyProfile/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
// @ is an alias to /src
import CreateProfile from '@/components/CreateProfile.vue'
import MyProfile from '@/components/MyProfile.vue'
import DeleteEmployee from  '@/components/DeleteEmployee.vue'

export default {
  name: 'Home',
  components: {
    CreateProfile,
    MyProfile,
    DeleteEmployee
  },
  mounted(){
    this.$store.dispatch('loadSkills')
    this.$store.dispatch('loadInterests')
  },
  data: () => ({
     showDeleteDialog: false
  }),
  methods: {

  },
  computed: {
    getProfile: function () {
      return this.$store.getters.profile
    }
  }
}
</script>