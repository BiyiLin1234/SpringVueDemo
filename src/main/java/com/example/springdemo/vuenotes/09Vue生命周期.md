> 生命周期钩子函数  https://vuejs.org/guide/essentials/lifecycle.html  
> Each Vue component instance goes through a series of initialization steps when it's crated.
> 
```js
<script>
    // 数据和界面分离
    const app = new Vue({
        el: '#app',
        data: {
            message: 'Hello',
            user: "small bee",
            movies: ['Titanic', 'Avatar', '大话西游'],
            counter: 0,
        },
        methods: {
            increase() {
                this.counter++
            },
            decrease() {
                this.counter--
            },
            plus10() {
                this.counter *= 10
            }
        },
        // Vue lifecycle hooks
        /**
         onBeforeMount: onBeforeMount,
         onMounted: onMounted,
         onBeforeUpdate: onBeforeUpdate,
         onUpdated: onUpdated,
         onBeforeUnmount: onBeforeUnmount,
         onUnmounted: onUnmounted,
         onErrorCaptured: onErrorCaptured,
         onActivated: onActivated,
         onDeactivated: onDeactivated,
         onServerPrefetch: onServerPrefetch,
         onRenderTracked: onRenderTracked,
         onRenderTriggered: onRenderTriggered
         */
        setup: function () {
            console.log("setup")
        },
        beforeCreate: function () {
            console.log("before create")
        },
        created: function () {
            console.log('created')
        },
        beforeMount: function () {
            console.log('beforeMount')
        },
        mounted: function () {
            console.log('mounted')
        },
        onBeforeUnmount: function () {
            console.log("onBeforeUnmount")
        },
        onUnmounted: function () {
            console.log("onUnmounted")
        }
    })
</script>
```