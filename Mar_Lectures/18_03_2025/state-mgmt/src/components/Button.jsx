import React, { useContext } from 'react';
import CompA from './CompA';
import CompB from './CompB';
import { countCtx } from '../state/context';

function Button({count}) {
    const obj = useContext(countCtx);
    
    function incr() {
        obj.setCount(obj.count + 1);
    }
  return (
    <div>
        <h2>Clickable Button : {count}</h2>
        <button onClick={incr}>CLICK HERE : {count}</button>
        <CompA count={count}/>
        <CompB />
    </div>
  )
}

export default Button