import React from 'react'
import Title from '../components/Title'
import Header from '../components/Header'
import Footer from '../components/Footer'

function Home() {
  return (
    <div>
        <Header />
            <Title msg = "Welcome to our National Bank" />
            <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Tempora aliquid doloribus, rem explicabo, earum dicta ut illum dolorum labore, fugiat delectus? Dolorum animi culpa accusantium repellat delectus laudantium vero. Error?
            </p>
        <Footer />
    </div>
  )
}

export default Home