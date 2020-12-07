<template>
  <div id="app">
    <v-app id="inspire">
      <v-card class="mx-auto" max-width="344">
        <v-img
          src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
          class="white--text align-end"
          gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          height="200px"
        >
          <v-card-title v-text="getProfile.displayName"></v-card-title>
        </v-img>

        <v-list-item style="text-align: left">
          <v-list-item-title>Education:</v-list-item-title>
          <v-list-item-subtitle>
            {{ getProfile.education }}
          </v-list-item-subtitle>
        </v-list-item>

        <v-list-item style="text-align: left">
          <v-list-item-content>
            <v-list-item-title>Skills:</v-list-item-title>
            <v-list-item-subtitle
              v-for="skill in getProfile.skills"
              :key="skill.name"
            >
              {{ skill.name }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item two-line style="text-align: left">
          <v-list-item-content>
            <v-list-item-title>Interests:</v-list-item-title>
            <v-list-item-subtitle
              v-for="interest in getProfile.interests"
              :key="interest.name"
            >
              {{ interest.name }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-card-actions>
          <v-btn color="orange lighten-2" text @click="show = !show">
            Edit Profile
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn icon disabled>
            <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show">
            <v-card-text>
              <v-form ref="form" lazy-validation>
                <v-text-field
                  id="displayName"
                  :counter="25"
                  v-model="form.editedDisplayName"
                  label="Name"
                  required
                  :placeholder="getProfile.displayName"
                ></v-text-field>

                <v-text-field
                  id="education"
                  :counter="25"
                  v-model="form.editedEducation"
                  label="Education"
                  required
                  :placeholder="getProfile.education"
                ></v-text-field>

                <v-select
                  v-model="form.editedSkills"
                  :items="getSkills.map((s) => s.name)"
                  attach
                  chips
                  label="Skills"
                  multiple
                  item-value="name"
                />

                <v-select
                  v-model="form.editedInterests"
                  :items="getInterests.map((i) => i.name)"
                  attach
                  chips
                  label="Interests"
                  multiple
                  item-value="name"
                />
              </v-form>
            </v-card-text>

            <v-btn @click="editProfile" color="primary" text> Save </v-btn>
          </div>
        </v-expand-transition>
      </v-card>
    </v-app>
  </div>
</template>


<script>
export default {
  data: () => ({  
    form: {
      profileId: 0,
      editedDisplayName: "",
      editedEducation: "",
      editedSkills: [],
      editedInterests: [],
    },
    show: false,
  }),

  methods: {
    editProfile: function () {
<<<<<<< HEAD
      this.form.profileId = this.getProfile.profileId
      this.$store.dispatch('editProfile', this.form)
=======
      this.axios
        .put(
          "http://192.168.99.100:8089/profile/",
          {
            profileId: this.profile.profileId,
            displayName: this.editedDisplayName,
            education: this.editedEducation,
            skills: this.profile.skills,
            interests: this.profile.interests
          })
          .then(response => {
            console.log(response.status);
          })
          .catch(error => {
            console.log(error.response)
          })
          this.axios
            .get("http://192.168.99.100:8089/profile/")
            .then((response) => (this.profile = response.data))
          this.show = false
>>>>>>> develop
    },
  },
  mounted() {
<<<<<<< HEAD
    this.$store.dispatch("loadProfile", this.$store.getters.userId);
=======
    this.axios
        .get("http://192.168.99.100:8089/profile/")
        .then((response) => (this.profile = response.data))
    this.axios
        .get("http://192.168.99.100:8089/skill/")
        .then((response) => (this.skills = response.data));
    this.axios
        .get("http://192.168.99.100:8089/interest/")
        .then((response) => (this.interests = response.data));
>>>>>>> develop
  },

  computed: {
    getProfile: function () {
      return this.$store.getters.profile;
    },
    getSkills: function (){
      return this.$store.getters.skills
    },
    getInterests: function (){
      return this.$store.getters.interests
    },
  },
};
</script>