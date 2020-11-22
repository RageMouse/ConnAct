<template>
    <div id="app">
        <v-app id="inspire">
            <v-card
            class="mx-auto"
            max-width="344"
            >
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="profile.displayName"></v-card-title>
            </v-img>


            <v-list-item style="text-align: left;">
                    <v-list-item-title>Education:</v-list-item-title>
                <v-list-item-subtitle> 
                     {{ profile.education }}
                </v-list-item-subtitle>
            </v-list-item>

            <v-list-item style="text-align: left;">
                <v-list-item-content>
                <v-list-item-title>Skills:</v-list-item-title>
                <v-list-item-subtitle v-for="skill in profile.skills" :key="skill.name">  
                    {{ skill.name }}
                </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
        
            <v-list-item two-line style="text-align: left;">
                <v-list-item-content>
                <v-list-item-title>Interests:</v-list-item-title>
                <v-list-item-subtitle v-for="interest in profile.interests" :key="interest.name">  
                    {{ interest.name }}
                </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
        
            <v-card-actions>
                <v-btn
                color="orange lighten-2"
                text
                @click="show = !show"
                >
                Edit Profile
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                icon
                disabled
                >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
            </v-card-actions>
        
            <v-expand-transition>
                <div v-show="show">
                <v-card-text>
                 <v-form ref="form" lazy-validation>
                    <v-text-field
                        id="displayName"
                        :counter="25"
                        v-model="editedDisplayName"
                        label="Name"
                        required
                        :placeholder="profile.displayName"
                    ></v-text-field>

                    <v-text-field
                        id="education"
                        :counter="25"
                        v-model="editedEducation"
                        label="Education"
                        required
                        :placeholder="profile.education"  
                    ></v-text-field>

                    <v-select 
                        v-model="profile.skills"
                        :items="skills.map(s => s.name)"
                        attach
                        chips
                        label="Skills"
                        multiple
                        item-value="name"
                    />

                    <v-select 
                        v-model="profile.interests"
                        :items="interests.map(i => i.name)"
                        attach
                        chips
                        label="Interests"
                        multiple
                        item-value="name"
                    />

                </v-form>
                </v-card-text>


                <v-btn @click="editProfile"
                    color="primary"
                    text
                >
                    Save
                </v-btn>
                </div>

            </v-expand-transition>


            </v-card>
        </v-app>
    </div>
</template>


<script>
export default {
  data: () => ({
    profile: {},
    profileId: 0,
    editedDisplayName: "",
    editedEducation: "",
    skills: [],
    interests: [],
    show: false,
  }),

  methods: {
    editProfile: function () {
      this.axios
        .put(
          "http://192.168.178.21:8089/profile/",
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
            .get("http://192.168.178.21:8089/profile/")
            .then((response) => (this.profile = response.data))
          this.show = false
    },
    
  },
  mounted() {
    this.axios
        .get("http://192.168.178.21:8089/profile/")
        .then((response) => (this.profile = response.data))
    this.axios
        .get("http://192.168.178.21:8089/skill/")
        .then((response) => (this.skills = response.data));
    this.axios
        .get("http://192.168.178.21:8089/interest/")
        .then((response) => (this.interests = response.data));
  },

  computed: {
      
  },
};
</script>