import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userid: 'test',
        user: null,
    },
    mutations: {
        updateUserid (state, message) {
            state.userid = message
            console.log(this.userid+'dsdsff')
        }
    },
    actions: {

    },
})