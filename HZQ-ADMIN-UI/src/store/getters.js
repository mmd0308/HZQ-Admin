const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  // 这里采用的ES6的写法，你可以替换成
  // name : function(state){return state.user.name}
  name: state => state.user.name,
  roles: state => state.user.roles,
  resCode: state => state.user.resCode,
  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters
}
export default getters
