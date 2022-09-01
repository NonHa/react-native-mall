var ee=Object.defineProperty,ie=Object.defineProperties;var te=Object.getOwnPropertyDescriptors;var O=Object.getOwnPropertySymbols;var le=Object.prototype.hasOwnProperty,ae=Object.prototype.propertyIsEnumerable;var U=(a,c,o)=>c in a?ee(a,c,{enumerable:!0,configurable:!0,writable:!0,value:o}):a[c]=o,f=(a,c)=>{for(var o in c||(c={}))le.call(c,o)&&U(a,o,c[o]);if(O)for(var o of O(c))ae.call(c,o)&&U(a,o,c[o]);return a},A=(a,c)=>ie(a,te(c));import{B as se,bL as G,C as oe,g0 as ne,ay as N,H as C,G as V,az as S,fG as B,fF as P,d as ce,fV as re,I as he,J as de,ai as ue,p as w,fT as pe,fJ as we,a as T,a3 as ve,K as E,M as fe,V as _e,o as s,q as u,g as z,f as i,y as d,dD as ge,c as p,w as _,a7 as x,ag as g,s as r,x as k,Z as j,e as be,f9 as me,a6 as H,an as ye,_ as xe,ar as ke,$ as Ce,g2 as J,U as Ie,aa as Ve}from"./index.9ea39232.js";const Se=se({modelValue:{type:[Boolean,String,Number],default:!1},value:{type:[Boolean,String,Number],default:!1},disabled:{type:Boolean,default:!1},width:{type:[String,Number],default:""},inlinePrompt:{type:Boolean,default:!1},activeIcon:{type:G,default:""},inactiveIcon:{type:G,default:""},activeText:{type:String,default:""},inactiveText:{type:String,default:""},activeColor:{type:String,default:""},inactiveColor:{type:String,default:""},borderColor:{type:String,default:""},activeValue:{type:[Boolean,String,Number],default:!0},inactiveValue:{type:[Boolean,String,Number],default:!1},name:{type:String,default:""},validateEvent:{type:Boolean,default:!0},id:String,loading:{type:Boolean,default:!1},beforeChange:{type:oe(Function)},size:{type:String,validator:ne},tabindex:{type:[String,Number]}}),Te={[N]:a=>C(a)||V(a)||S(a),[B]:a=>C(a)||V(a)||S(a),[P]:a=>C(a)||V(a)||S(a)},Ee=["onClick"],ze=["id","aria-checked","aria-disabled","name","true-value","false-value","disabled","tabindex","onKeydown"],Ne=["aria-hidden"],Be=["aria-hidden"],Pe=["aria-hidden"],De=["aria-hidden"],Me={name:"ElSwitch"},Fe=ce(A(f({},Me),{props:Se,emits:Te,setup(a,{expose:c,emit:o}){const l=a,D="ElSwitch",L=ke(),{formItem:b}=re(),q=he(),t=de("switch");ue({from:'"value"',replacement:'"model-value" or "v-model"',scope:D,version:"2.3.0",ref:"https://element-plus.org/en-US/component/switch.html#attributes",type:"Attribute"},w(()=>{var e;return!!((e=L.vnode.props)!=null&&e.value)}));const{inputId:W}=pe(l,{formItemContext:b}),m=we(w(()=>l.loading)),I=T(l.modelValue!==!1),v=T(),Z=T(),$=w(()=>[t.b(),t.m(q.value),t.is("disabled",m.value),t.is("checked",n.value)]),Q=w(()=>({width:ve(l.width)}));E(()=>l.modelValue,()=>{I.value=!0}),E(()=>l.value,()=>{I.value=!1});const M=w(()=>I.value?l.modelValue:l.value),n=w(()=>M.value===l.activeValue);[l.activeValue,l.inactiveValue].includes(M.value)||(o(N,l.inactiveValue),o(B,l.inactiveValue),o(P,l.inactiveValue)),E(n,e=>{var h;v.value.checked=e,l.validateEvent&&((h=b==null?void 0:b.validate)==null||h.call(b,"change").catch(Y=>fe()))});const y=()=>{const e=n.value?l.inactiveValue:l.activeValue;o(N,e),o(B,e),o(P,e),Ce(()=>{v.value.checked=n.value})},F=()=>{if(m.value)return;const{beforeChange:e}=l;if(!e){y();return}const h=e();[J(h),C(h)].includes(!0)||Ie(D,"beforeChange must return type `Promise<boolean>` or `boolean`"),J(h)?h.then(K=>{K&&y()}).catch(K=>{}):h&&y()},R=w(()=>t.cssVarBlock(f(f(f({},l.activeColor?{"on-color":l.activeColor}:null),l.inactiveColor?{"off-color":l.inactiveColor}:null),l.borderColor?{"border-color":l.borderColor}:null))),X=()=>{var e,h;(h=(e=v.value)==null?void 0:e.focus)==null||h.call(e)};return _e(()=>{v.value.checked=n.value}),c({focus:X}),(e,h)=>(s(),u("div",{class:d(i($)),style:H(i(R)),onClick:ye(F,["prevent"])},[z("input",{id:i(W),ref_key:"input",ref:v,class:d(i(t).e("input")),type:"checkbox",role:"switch","aria-checked":i(n),"aria-disabled":i(m),name:e.name,"true-value":e.activeValue,"false-value":e.inactiveValue,disabled:i(m),tabindex:e.tabindex,onChange:y,onKeydown:ge(F,["enter"])},null,42,ze),!e.inlinePrompt&&(e.inactiveIcon||e.inactiveText)?(s(),u("span",{key:0,class:d([i(t).e("label"),i(t).em("label","left"),i(t).is("active",!i(n))])},[e.inactiveIcon?(s(),p(i(g),{key:0},{default:_(()=>[(s(),p(x(e.inactiveIcon)))]),_:1})):r("v-if",!0),!e.inactiveIcon&&e.inactiveText?(s(),u("span",{key:1,"aria-hidden":i(n)},k(e.inactiveText),9,Ne)):r("v-if",!0)],2)):r("v-if",!0),z("span",{ref_key:"core",ref:Z,class:d(i(t).e("core")),style:H(i(Q))},[e.inlinePrompt?(s(),u("div",{key:0,class:d(i(t).e("inner"))},[e.activeIcon||e.inactiveIcon?(s(),u(j,{key:0},[e.activeIcon?(s(),p(i(g),{key:0,class:d([i(t).is("icon"),i(n)?i(t).is("show"):i(t).is("hide")])},{default:_(()=>[(s(),p(x(e.activeIcon)))]),_:1},8,["class"])):r("v-if",!0),e.inactiveIcon?(s(),p(i(g),{key:1,class:d([i(t).is("icon"),i(n)?i(t).is("hide"):i(t).is("show")])},{default:_(()=>[(s(),p(x(e.inactiveIcon)))]),_:1},8,["class"])):r("v-if",!0)],64)):e.activeText||e.inactiveIcon?(s(),u(j,{key:1},[e.activeText?(s(),u("span",{key:0,class:d([i(t).is("text"),i(n)?i(t).is("show"):i(t).is("hide")]),"aria-hidden":!i(n)},k(e.activeText.substring(0,3)),11,Be)):r("v-if",!0),e.inactiveText?(s(),u("span",{key:1,class:d([i(t).is("text"),i(n)?i(t).is("hide"):i(t).is("show")]),"aria-hidden":i(n)},k(e.inactiveText.substring(0,3)),11,Pe)):r("v-if",!0)],64)):r("v-if",!0)],2)):r("v-if",!0),z("div",{class:d(i(t).e("action"))},[e.loading?(s(),p(i(g),{key:0,class:d(i(t).is("loading"))},{default:_(()=>[be(i(me))]),_:1},8,["class"])):r("v-if",!0)],2)],6),!e.inlinePrompt&&(e.activeIcon||e.activeText)?(s(),u("span",{key:1,class:d([i(t).e("label"),i(t).em("label","right"),i(t).is("active",i(n))])},[e.activeIcon?(s(),p(i(g),{key:0},{default:_(()=>[(s(),p(x(e.activeIcon)))]),_:1})):r("v-if",!0),!e.activeIcon&&e.activeText?(s(),u("span",{key:1,"aria-hidden":!i(n)},k(e.activeText),9,De)):r("v-if",!0)],2)):r("v-if",!0)],14,Ee))}}));var Ke=xe(Fe,[["__file","/home/runner/work/element-plus/element-plus/packages/components/switch/src/switch.vue"]]);const Ae=Ve(Ke);export{Ae as E};
