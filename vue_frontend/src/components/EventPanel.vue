<template>
    <div>
        <Event v-for="event in events" :id="event.id" :event="event" />
    </div>
</template>

<script>
    import axios from 'axios';
    import Event from './Event.vue';

    export default {
        name: "EventPanel",
        components: {Event},

        data() {
            return {
                events: []
            }
        },

        async created() {
            let kek = await axios.get('/events/getEvents');
            this.events = kek.data;
            console.log(this.events);
        }
    }
</script>

<style scoped>

</style>