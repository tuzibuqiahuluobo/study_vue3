<template>
  <div class="music-player">
    <div
      class="music-player__record-wrapper"
      v-bind:class="{ 'is-playing': isPlaying }"
    >
      <div class="music-player__record"></div>
    </div>
    <div class="music-player__control">
      <button v-on:click="playMusic" v-show="!isPlaying">播放</button>
      <button v-on:click="stopMusic" v-show="isPlaying">停止</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "MusicPlayer",
  data() {
    return {
      isPlaying: false,
    };
  },
  methods: {
    playMusic() {
      this.isPlaying = true;
    },
    stopMusic() {
      this.isPlaying = false;
    },
  },
};
</script>

<style scoped>
.music-player {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.music-player__record-wrapper {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  border: 5px solid #ddd;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: transform 2s linear;
}

.music-player__record {
  width: 150px;
  height: 150px;

  border-radius: 50%;
  background-color: #ddd;
  background-image: url("../assets/bjtx.jpg");
  background-size: 100% 100%;
  animation: rotate 2s linear infinite;
}

@keyframes rotate {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}

.music-player__record-wrapper.is-playing {
  animation-play-state: paused;
}
</style>
