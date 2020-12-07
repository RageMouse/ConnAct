<template>
  <v-container>
    <v-card class="elevation-12 mx-auto" max-width="400">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Create Profile</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form ref="form" lazy-validation>
          <v-text-field
            id="displayName"
            v-model="form.displayName"
            :counter="25"
            :rules="nameRules"
            label="Name"
            required
          ></v-text-field>

          <v-text-field
            id="education"
            v-model="form.education"
            :counter="25"
            :rules="nameRules"
            label="Education"
            required
          ></v-text-field>

          <v-select
            v-model="form.skills"
            :items="getSkills.map((skill) => skill.name)"
            attach
            chips
            label="Add skills"
            multiple
          />

          <v-select
            v-model="form.interests"
            :items="getInterests.map((interest) => interest.name)"
            attach
            chips
            label="Add interests"
            multiple
          />
        </v-form>
      </v-card-text>
      <v-card-actions
        ><v-btn id="validateButton" color="success" @click="create"
          >Sumbit</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    form:{
      displayName: "",
      education: "",
      skills: [],
      interests: [],
      userId: 0,
    },
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 25) || "Name must be less than 25 characters",
    ],
  }),

  mounted() {

  },
  methods: {
    create: function () {
      this.form.userId = this.$store.getters.userId
      this.$store.dispatch("createProfile", this.form)
    },
    send: function () {
      this.axios
        .post(
          "http://192.168.99.100:8089/profile/",
          {
            displayName: this.displayName,
            education: this.education,
            skills: this.selectedSkills,
            interests: this.selectedInterests
          })
          .then(response => {
            console.log(response.status);
            if (response.status !== 204) {
              this.alertSucces = true;
            }
          })
          .catch(error => {
            console.log(error.response);
          });
          this.hideForm()
    },
  },
  computed: {
    getSkills: function () {
      return this.$store.getters.skills;
    },
    getInterests: function () {
      return this.$store.getters.interests;
    },
  },
};
</script>