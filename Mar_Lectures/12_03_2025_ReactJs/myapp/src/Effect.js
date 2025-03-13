import React, { useEffect, useState } from 'react'

function Effect() {
    const [a, setA] = useState(0);
    const [b, setB] = useState(0);

    function updateA() {
        setA(a+1); 
    }
    function updateB() {
        setB(b+1);
    }

    // change in b wil trigger the useEffecrt
    useEffect(() => {
        console.log("Component Mounted Successfully");
    },[b])
  return (
    <>
        <h2>This is from effect component</h2>
        <h3>A : {a}</h3>
        <h3>B : {b}</h3>
        <button onClick={updateA}>A</button>
        <button onClick={updateB}>B</button>
    </>
  )
}

export default Effect;