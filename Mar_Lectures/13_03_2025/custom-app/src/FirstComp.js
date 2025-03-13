import React from 'react'
import useCounter from './useCounter'

function FirstComp() {
    const [cnt, incr, decr] = useCounter(5);
  return (
    <>
        <h2>First Component</h2>
        <span>Count : {cnt}</span>
        <br></br>
        <button onClick={incr}>INCREMENT</button>
        <button onClick={decr}>DECREMENT</button>
    </>
  )
}

export default FirstComp