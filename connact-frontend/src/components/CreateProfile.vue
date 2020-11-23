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
            v-model="displayName"
            :counter="25"
            :rules="nameRules"
            label="Name"
            required
          ></v-text-field>

          <v-text-field
            id="education"
            v-model="education"
            :counter="25"
            :rules="nameRules"
            label="Education"
            required
          ></v-text-field>

          <v-select
            v-model="selectedSkills"
            :items="getSkills.map((skill) => skill.name)"
            attach
            chips
            label="Add skills"
            multiple
          />

          <v-select
            v-model="selectedInterests"
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
    <br />
    <v-alert
      class="elevation-12 mx-auto"
      outlined
      type="success"
      text
      :value="alertSucces"
      max-width="1000px"
      style="width: 400px"
    >
      Profile has succesfully been created!
    </v-alert>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    displayName: "",
    education: "",
    skills: [],
    selectedSkills: [],
    interests: [],
    selectedInterests: [],
    alertSucces: false,
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 25) || "Name must be less than 25 characters",
    ],
    createProfileModel: {},
  }),

  mounted() {

  },
  methods: {
    create: function () {
      this.createProfileModel.displayName = this.displayName
      this.createProfileModel.education = this.education
      this.createProfileModel.skills = this.selectedSkills
      this.createProfileModel.interests = this.selectedInterests
      
      this.$store.dispatch("createProfile", this.createProfileModel)
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