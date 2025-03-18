import React, { useContext } from 'react'
import { countCtx } from '../state/context';


function CompB() {
    const obj = useContext(countCtx);

    return (
    <div>
        <h2>From Component B : {obj.count}</h2>
    </div>
  )
}

export default CompB