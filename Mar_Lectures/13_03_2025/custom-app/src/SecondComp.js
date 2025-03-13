import React from 'react'
import useCounter from './useCounter'

function SecondComp() { 
    const [cnt, incr, decr] = useCounter(9);
  return (
    <>
        <h2>Second Component</h2>
        <span>Count : {cnt}</span>
        <br></br>
        <button onClick={incr}>INCREMENT</button>
        <button onClick={decr}>DECREMENT</button>
    </>
  )
}

export default SecondComp