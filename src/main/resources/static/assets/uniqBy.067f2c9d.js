import{bU as w,bV as R,bW as C,bX as O,bY as g,bZ as E,b_ as P,b$ as M,c0 as S,c1 as y,z as L,c2 as o,c3 as I,c4 as p,c5 as G,c6 as D,c7 as F}from"./index.9ea39232.js";function d(){}var N=1,T=2;function U(n,r,e,s){var f=e.length,l=f,h=!s;if(n==null)return!l;for(n=Object(n);f--;){var i=e[f];if(h&&i[2]?i[1]!==n[i[0]]:!(i[0]in n))return!1}for(;++f<l;){i=e[f];var t=i[0],c=n[t],u=i[1];if(h&&i[2]){if(c===void 0&&!(t in n))return!1}else{var a=new w;if(s)var A=s(c,u,t,n,r,a);if(!(A===void 0?R(u,c,N|T,s,a):A))return!1}}return!0}function _(n){return n===n&&!C(n)}function $(n){for(var r=O(n),e=r.length;e--;){var s=r[e],f=n[s];r[e]=[s,f,_(f)]}return r}function b(n,r){return function(e){return e==null?!1:e[n]===r&&(r!==void 0||n in Object(e))}}function q(n){var r=$(n);return r.length==1&&r[0][2]?b(r[0][0],r[0][1]):function(e){return e===n||U(e,n,r)}}var Y=1,v=2;function x(n,r){return g(n)&&_(r)?b(E(n),r):function(e){var s=P(e,n);return s===void 0&&s===r?M(e,n):R(r,s,Y|v)}}function K(n){return function(r){return r==null?void 0:r[n]}}function W(n){return function(r){return S(r,n)}}function Z(n){return g(n)?K(E(n)):W(n)}function B(n){return typeof n=="function"?n:n==null?y:typeof n=="object"?L(n)?x(n[0],n[1]):q(n):Z(n)}var H=1/0,X=o&&1/I(new o([,-0]))[1]==H?function(n){return new o(n)}:d,J=X,Q=200;function z(n,r,e){var s=-1,f=G,l=n.length,h=!0,i=[],t=i;if(e)h=!1,f=D;else if(l>=Q){var c=r?null:J(n);if(c)return I(c);h=!1,f=F,t=new p}else t=r?[]:i;n:for(;++s<l;){var u=n[s],a=r?r(u):u;if(u=e||u!==0?u:0,h&&a===a){for(var A=t.length;A--;)if(t[A]===a)continue n;r&&t.push(a),i.push(u)}else f(t,a,e)||(t!==i&&t.push(a),i.push(u))}return i}function m(n,r){return n&&n.length?z(n,B(r)):[]}export{B as a,z as b,U as c,q as d,x as e,K as f,$ as g,d as n,Z as p,m as u};
