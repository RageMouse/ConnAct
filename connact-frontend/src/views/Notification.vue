<template>
  <v-container>
    
    <projectinvite
    class="project-invite"
        v-for="(invite, index) in invites"
        :key="'project: ' + index"
        :employee="invite.employee.userName"
        :event="invite.event.eventName"
        :requestid="invite.id"/>
  </v-container>
</template>
<script>
// @ is an alias to /src
import projectinvite from '@/components/ProjectInvite.vue'

export default {
  name: 'test',
  components: {
    projectinvite
  },
  data() {
    return {
    };
  },
  mounted(){
    this.$store.dispatch("loadInvites", this.$store.state.userid)
  },
  computed: {
    invites() {
      return this.$store.getters.invites;
    },
    
    userid: {
        get () {
        return this.$store.state.userid
          }
        },
  },
}
</script>