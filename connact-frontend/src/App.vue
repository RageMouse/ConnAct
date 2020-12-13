<template>
  <NavbarDefault v-if="this.userid==null"></NavbarDefault>
  
  <NavbarLogged v-else></NavbarLogged>
</template>
<script>

import NavbarLogged from './components/NavbarLogged';
import NavbarDefault from './components/NavbarDefault';

export default {
  name: 'App',
  components: {
    NavbarLogged,
    NavbarDefault
  },
  computed:{
        userid: {
        get () {
        return this.$store.state.userid
          }
        },
  },

  data: () => ({
    //
  }),
};
</script>