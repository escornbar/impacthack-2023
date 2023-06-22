import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default function CreditFactorCard({
  title,
  figure,
}: {
  title: string
  figure: string
}) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>
          <b>{title}</b>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <p>{figure}</p>
      </CardContent>
    </Card>
  )
}
