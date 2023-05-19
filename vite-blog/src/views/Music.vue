<template>
  <div>
    <music-player :src="audioSrc"></music-player>
  </div>
</template>

<script>
import MusicPlayer from "@/components/MusicPlayer.vue";

export default {
  components: {
    MusicPlayer,
  },
  data() {
    return {
      audioSrc: "../assets/彼女は旅に出る.mp3",
    };
  },
};
</script>
