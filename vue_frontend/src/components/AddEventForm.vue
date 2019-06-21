<template>
    <div>
        <b>Choose sport</b><br>
        <select>
            <option v-for="sport in sports" v-bind:value="sport.sport">{{sport.sport}}</option>
        </select>
        <br>

        <b>Team</b>
        <select>
            <option v-for="sport in sports" v-bind:value="sport.sport">{{sport.sport}}</option>
        </select>

        <b>    VS    </b>


        <b>Team</b>
        <select>
            <option v-for="sport in sports" v-bind:value="sport.sport">{{sport.sport}}</option>
        </select>
        <br>

        <b>Date</b>
        <input type="text">
        <br>
        <b>Time</b>
        <input type="text">
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "AddEventForm",
        data: function () {
            return {
                sports : null
            }
        },

        async created() {
            const sortsData = await axios.get('/directories/getSports');
            this.sports = sortsData.data;
            console.log(this.sports);
        }
    }
</script>

<style scoped>

</style>