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
            label="education"
            required
          ></v-text-field>

            <v-select
                v-model="skills"
                :items="skills.map(skill => skill.name)"
                attach
                chips
                label="Add Skills"
                multiple
            />

            <v-select
                v-model="interests"
                :items="interests.map(interest => interest.name)"
                attach
                chips
                label="Add interests"
                multiple
            />

        </v-form>
      </v-card-text>
      <v-card-actions
        ><v-btn id="validateButton" color="success" @click="send">Sumbit</v-btn>
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
    skills: [{"name": "skill1"},{"name":"skill2"}],
    interests: [{"name": "interest1"},{"name":"interest2"}],
    alertSucces: false,
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => (v && v.length <= 25) || "Name must be less than 25 characters",
    ],
  }),

  methods: {
    send: function () {
      this.axios
        .post(
          "http://192.168.99.100:8089/profile/",
          {
            displayName: this.displayName,
            education: this.education,
            skills: this.skills,
            interests: this.interests
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
    },
  },

  computed: {

  },
};
</script>