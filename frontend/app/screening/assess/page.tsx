"use client"

import { useEffect, useState } from "react"
import { Loader2 } from "lucide-react"

import CreditFactorCard from "@/components/CreditFactorCard"

const creditFactors = [
  {
    title: "Payment History",
    figure: "99%",
  },
  {
    title: "Credit Utilization",
    figure: "30%",
  },
  {
    title: "Length of Credit History",
    figure: "5 years",
  },
  {
    title: "Public Records",
    figure: "No records",
  },
  {
    title: "Business Size and Industry Risk",
    figure: "Medium",
  },
  {
    title: "Financial Statements",
    figure: "Available",
  },
  {
    title: "Business Structure and Legal Entity",
    figure: "Corporation",
  },
  {
    title: "Business and Owner's Credit History",
    figure: "Good",
  },
  {
    title: "Industry and Economic Factors",
    figure: "Stable",
  },
  {
    title: "Credit Inquiries",
    figure: "2",
  },
]

export default function AssessCreditScore() {
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const timeout = setTimeout(() => {
      setIsLoading(false)
    }, 2500)

    return () => clearTimeout(timeout)
  }, [])
  return (
    <>
      <h1 className="my-8 text-4xl font-bold tracking-tight">Screening</h1>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto modi
        officia exercitationem voluptates, quaerat quia sit provident voluptas
        facilis aliquam nihil animi, dolor quam ipsam, minus officiis?
        Laboriosam, voluptate esse.
      </p>
      <h2 className="my-8 text-3xl font-bold tracking-tight">
        Your Credit Score
      </h2>
      {isLoading ? (
        <div className="grid items-center justify-center">
          <Loader2 className="h-[2rem] w-[2rem] animate-spin" />
        </div>
      ) : (
        // Your actual content when the loader is not visible.
        <div>
          <h3 className="my-8 text-2xl font-bold tracking-tight">
            Credit Factors
          </h3>
          <div className="grid items-center w-full grid-cols-3 gap-4">
            {creditFactors.map((creditFactor, index) => (
              <CreditFactorCard
                key={index}
                title={creditFactor.title}
                figure={creditFactor.figure}
              />
            ))}
          </div>
        </div>
      )}
    </>
  )
}
