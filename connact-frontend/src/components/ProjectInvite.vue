<template>
  <v-container>
    <v-card class="elevation-12 mx-auto" max-width="800">
        <v-card-text>
            <v-btn
                    color="green"
                    text
                    @click.native.prevent="acceptRequest(requestid)"
                  >
                    Accept
            </v-btn>
            <v-btn
                    color="red"
                    text
                    @click.native.prevent="acceptRequest(requestid)"
                  >
                    Decline
            </v-btn>
            Invite van {{employee}} voor event {{event}} 
            

        </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
    props: {
    employee: String,
    event: String,
    requestid: String
  },
  data () {
    return {
      
      
    }
  },
  methods: {
        acceptRequest: function (id) {
       this.$store.dispatch('acceptRequest', id)
    },
  }
}
</script>