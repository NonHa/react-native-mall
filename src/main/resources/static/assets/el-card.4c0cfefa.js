var h=Object.defineProperty,b=Object.defineProperties;var v=Object.getOwnPropertyDescriptors;var s=Object.getOwnPropertySymbols;var u=Object.prototype.hasOwnProperty,y=Object.prototype.propertyIsEnumerable;var l=(r,e,a)=>e in r?h(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,c=(r,e)=>{for(var a in e||(e={}))u.call(e,a)&&l(r,a,e[a]);if(s)for(var a of s(e))y.call(e,a)&&l(r,a,e[a]);return r},t=(r,e)=>b(r,v(e));import{B as g,C as f,d as m,J as w,o as n,q as i,y as d,f as o,Q as p,h as _,x,s as C,g as S,a6 as k,_ as B,aa as E}from"./index.9ea39232.js";const N=g({header:{type:String,default:""},bodyStyle:{type:f([String,Object,Array]),default:""},shadow:{type:String,values:["always","hover","never"],default:"always"}}),$={name:"ElCard"},z=m(t(c({},$),{props:N,setup(r){const e=w("card");return(a,V)=>(n(),i("div",{class:d([o(e).b(),o(e).is(`${a.shadow}-shadow`)])},[a.$slots.header||a.header?(n(),i("div",{key:0,class:d(o(e).e("header"))},[p(a.$slots,"header",{},()=>[_(x(a.header),1)])],2)):C("v-if",!0),S("div",{class:d(o(e).e("body")),style:k(a.bodyStyle)},[p(a.$slots,"default")],6)],2))}}));var P=B(z,[["__file","/home/runner/work/element-plus/element-plus/packages/components/card/src/card.vue"]]);const q=E(P);export{q as E};
