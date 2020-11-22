<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-text>
            <v-container>
              <form @submit.prevent="onLogin">
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                        name="username"
                        label="username"
                        id="username"
                        v-model="username"
                        type="username"
                        required></v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                        name="password"
                        label="Password"
                        id="password"
                        v-model="password"
                        type="password"
                        required></v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  {{userid}}
                  <v-flex xs12>
                    <v-btn @click.native.prevent="send" type="submit">Login</v-btn>
                  </v-flex>
                </v-layout>
                <v-alert
                  class="elevation-12 mx-auto"
                  outlined
                  type="warning"
                  text
                  :value="alertFail"
                  max-width="1000px"
              >
                {{this.errormessage}}
              </v-alert>
              <v-alert
                  class="elevation-12 mx-auto"
                  outlined
                  type="succes"
                  text
                  :value="alertSucces"
                  max-width="1000px"
              >
                Logged in
              </v-alert>

              </form>
            </v-container>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'login',
  computed:{
    userid: {
      get () {
        return this.$store.state.userid
      }
    },
  },
  data () {
    return {
      username: '',
      password: '',
      errormessage:'',
      alertFail:false,
      alertSucces:false,
    }
  },

  methods: {
    login () {
      this.$store.dispatch('login', {email: this.email, password: this.password})
    },
    send: function () {
      this.axios
          .post("http://192.168.178.21:8089/employee/login", {
            userName: this.username,
            password: this.password,
          })
          .then((response) => {
            this.username = response.data.userName
            this.$store.commit('updateUserid', response.data.employeeId)
            console.log(response.status + 'statussss');
            console.log(response.data.lolname + 'ID');

            if (response.status !== 204) {
              this.alertSucces = true;
            }
          })
          .catch((error) => {
            this.errormessage = 'Wrong logindata'
              this.alertFail = true;
            console.log(error.response);
          });
    },
  }
}
</script>