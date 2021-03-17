<template>
  <div v-bind:class="{'component select': true, 'has-error': $props.error}">
    <label v-bind:for="id" v-if="$props.label">{{$props.label + ($props.required ? '*' : '')}}</label>
    <select
      v-model="localValue"
      v-bind:id="id"
      v-bind:type="$props.type"
      v-bind:name="$props.name"
      v-bind:placeholder="$props.placeholder"
      v-bind:disabled="$props.disabled"
    >
      <slot></slot>
    </select>
    <span class="select__error" v-if="$props.error">{{$props.error}}</span>
  </div>
</template>

<script>
export default {
  name: 'Select',
  props: {
    name: {
      type: String,
      default: 'input'
    },
    placeholder: {
      type: String,
      default: null
    },
    label: {
      type: String,
      default: null
    },
    disabled: {
      type: Boolean,
      default: false
    },
    error: {
      type: String,
      default: null
    },
    value: {
      type: [String, Number],
      default: () => null
    },
    required: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      localValue: null
    }
  },
  created () {
    if (this.$props.value) this.localValue = this.$props.value
  },
  computed: {
    id () {
      return ('input-' + this._uid)
    }
  },
  watch: {
    '$props.value' (value) {
      if (value !== this.localValue) this.localValue = value
    },
    'localValue' (value) {
      this.$emit('input', value)
    }
  }
}
</script>

<style scoped>
.component.select{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  margin: 0 0 10px 0;
}
.component.select label{}
.component.select select{}
.component.select .select__error{
  font-size: 12px;
  line-height: 14px;
  color: red;
}
.component.select.has-error select{
  border-color: red;
}
</style>
