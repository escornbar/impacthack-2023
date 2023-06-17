import Link from "next/link"

import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"

// A personal financial statement and personal federal income tax returns from the last one to three years
// Projected startup cost estimates
// Projected balance sheets and income statements for at least two years
// Projected cash flow statement for at least the first 12 months
// Evidence of ownership interests in assets, such as leases and contracts, and collateral
// A business plan that includes a narrative explaining the specific use for the requested funds, how the money will assist the business and how the borrowed funds will be repaid (repayment sources and duration of repayment period), including identifying any assumptions used in developing your projected financial
// A personal resume, or at least a written explanation of your relevant past business experience
// Letters of reference recommending you as a reputable and reliable business person may also help your chances for a loan approval

const docs = [
  "Financial Statement",
  "Tax Returns",
  "Startup Cost Estimates",
  "Balance Sheets",
  "Income Statements",
  "Cash Flow Statements",
  "Evidence of Ownership",
  "Business Plan",
  "Resume",
  "Letters of Reference",
]

export default function ScreeningPage() {
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
        Upload Document
      </h2>
      <div className="grid grid-cols-3 gap-8">
        {docs.map((doc, index) => (
          <div className="grid w-full items-center gap-1.5" key={index}>
            <Label htmlFor="picture">{doc}</Label>
            <Input id="picture" type="file" />
          </div>
        ))}
      </div>
      <div className="grid justify-end">
        <Button>
          <Link href="/screening/assess">Next</Link>
        </Button>
      </div>
    </>
  )
}
