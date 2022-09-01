var M=Object.defineProperty;var I=Object.getOwnPropertySymbols;var P=Object.prototype.hasOwnProperty,R=Object.prototype.propertyIsEnumerable;var k=(o,e,t)=>e in o?M(o,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):o[e]=t,g=(o,e)=>{for(var t in e||(e={}))P.call(e,t)&&k(o,t,e[t]);if(I)for(var t of I(e))R.call(e,t)&&k(o,t,e[t]);return o};var w=(o,e,t)=>new Promise((d,c)=>{var p=n=>{try{u(t.next(n))}catch(m){c(m)}},r=n=>{try{u(t.throw(n))}catch(m){c(m)}},u=n=>n.done?d(n.value):Promise.resolve(n.value).then(p,r);u((t=t.apply(o,e)).next())});import{d as A,r as U,a as v,u as j,b as q,o as f,c as B,w as h,e as i,f as s,g as l,h as b,E as G,i as K,j as O,L as C,l as V,k as Y,m as H,n as J,p as Q,q as W,A as X,s as F,t as Z,v as E,x as L,y as S}from"./index.9ea39232.js";import{E as ee,a as te}from"./el-form-item.f36f1992.js";import{u as oe}from"./product.2dbecb13.js";import{u as le,a as se}from"./useLogin.302d86a7.js";import"./table.e172cbb0.js";var ne="/assets/login-box-bg.9027741f.svg";const ae=l("div",{class:"title-container"},[l("h3",{class:"title"},"\u4F0A\u767E\u4E3D\u7ECF\u8425\u7BA1\u7406\u7CFB\u7EDF")],-1),ie=l("span",{class:"show-pwd"},null,-1),re=l("label",{for:"rememberMe",title:"\u4E0B\u6B21\u4E0D\u9700\u8981\u518D\u767B\u5F55",style:{color:"#6f6f6f","font-weight":"normal",float:"left"}},[l("input",{type:"checkbox",style:{height:"16px",width:"16px"},id:"rememberMe",name:"rememberMe"}),b(" \u8BB0\u4F4F\u5BC6\u7801 ")],-1),ce=l("label",{for:"autoLogin",title:"\u81EA\u52A8\u767B\u5F55",style:{color:"#6f6f6f","font-weight":"normal",float:"right"}},[l("input",{type:"checkbox",style:{height:"16px",width:"16px","margin-left":"20px"},id:"autoLogin",name:"autoLogin"}),b(" \u81EA\u52A8\u767B\u5F55 ")],-1),ue=b(" \u767B\u5F55 "),de=A({__name:"LoginForm",setup(o){const e=U({password:"",account:""});let t=v(!1);const d=v(),{getFormRules:c}=le(),{validForm:p}=se(d),{notification:r,createErrorModal:u}=j(),n=q(),m=oe();function T(){return w(this,null,function*(){yield p(()=>w(this,null,function*(){try{t.value=!0;const a=yield n.login({password:e.password,username:e.account,mode:"none"});a&&(r.success({title:"sys.login.loginSuccessTitle",message:`sys.login.loginSuccessDesc: ${a.realName}`}),m.getAllList())}catch(a){u({title:"sys.api.errorTip",message:a.message||"sys.api.networkExceptionMsg"})}finally{t.value=!1}}))})}return(z,a)=>{const y=G,x=ee,$=K,N=te;return f(),B(N,{ref_key:"formRef",ref:d,model:e,class:"login-form",autocomplete:"on","label-position":"left",size:"small",rules:s(c)},{default:h(()=>[ae,i(x,{prop:"userName"},{default:h(()=>[i(y,{ref:"userName_input",modelValue:e.account,"onUpdate:modelValue":a[0]||(a[0]=_=>e.account=_),placeholder:"\u8BF7\u8F93\u5165\u7528\u6237\u540D"},null,8,["modelValue"])]),_:1}),i(x,{prop:"password"},{default:h(()=>[i(y,{ref:"password_input",type:"password",modelValue:e.password,"onUpdate:modelValue":a[1]||(a[1]=_=>e.password=_),placeholder:"\u8BF7\u8F93\u5165\u5BC6\u7801"},null,8,["modelValue"]),ie]),_:1}),i(x,{style:{height:"30px",border:"none"}},{default:h(()=>[re,ce]),_:1}),i($,{loading:s(t),class:"login-btn",size:"small",type:"primary",onClick:T},{default:h(()=>[ue]),_:1},8,["loading"])]),_:1},8,["model","rules"])}}}),D=O(),pe=D.get(C)||V,me=Y({id:"app-locale",state:()=>({localInfo:pe}),getters:{getShowPicker(){var o;return!!((o=this.localInfo)!=null&&o.showPicker)},getLocale(){var o,e;return(e=(o=this.localInfo)==null?void 0:o.locale)!=null?e:"zh_CN"}},actions:{setLocaleInfo(o){this.localInfo=g(g({},this.localInfo),o),D.set(C,this.localInfo)},initLocale(){this.setLocaleInfo(g(g({},V),this.localInfo))}}});const ge={class:"-enter-x xl:hidden"},he={class:"container relative h-full py-2 mx-auto sm:px-10"},fe={class:"flex h-full"},xe={class:"hidden min-h-full pl-4 mr-4 xl:flex xl:flex-col xl:w-6/12"},_e={class:"my-auto"},we=["alt"],Be={class:"mt-10 font-medium text-white -enter-x"},be={class:"inline-block mt-4 text-3xl"},ye={class:"mt-5 font-normal text-white text-md dark:text-gray-500 -enter-x"},Ie={class:"flex w-full h-full py-5 xl:h-auto xl:py-0 xl:my-0 xl:w-6/12"},Ae=A({__name:"index",props:{sessionTimeout:{type:Boolean}},setup(o){const e=H(),{prefixCls:t}=J("login"),c=me().getShowPicker,p=Q(()=>{var r;return(r=e==null?void 0:e.title)!=null?r:""});return(r,u)=>(f(),W("div",{class:S([s(t),"relative w-full h-full px-4"])},[!o.sessionTimeout&&s(c)?(f(),B(s(X),{key:0,class:"absolute text-white top-4 right-4 enter-x xl:text-gray-600",showText:!1})):F("",!0),o.sessionTimeout?F("",!0):(f(),B(s(Z),{key:1,class:"absolute top-3 right-7 enter-x"})),l("span",ge,[i(s(E),{alwaysShowTitle:!0})]),l("div",he,[l("div",fe,[l("div",xe,[i(s(E),{class:"-enter-x"}),l("div",_e,[l("img",{alt:s(p),src:ne,class:"w-1/2 -mt-16 -enter-x"},null,8,we),l("div",Be,[l("span",be,L("sys.login.signInTitle"),1)]),l("div",ye,L("sys.login.signInDesc"),1)])]),l("div",Ie,[l("div",{class:S([`${s(t)}-form`,"relative w-full px-5 py-8 mx-auto my-auto rounded-md shadow-md xl:ml-16 xl:bg-transparent sm:px-8 xl:p-4 xl:shadow-none sm:w-3/4 lg:w-2/4 xl:w-auto enter-x"])},[i(de)],2)])])])],2))}});export{Ae as default};
