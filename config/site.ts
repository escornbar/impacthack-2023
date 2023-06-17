export type SiteConfig = typeof siteConfig

export const siteConfig = {
  name: "ImpactHack",
  description:
    "Beautifully designed components built with Radix UI and Tailwind CSS.",
  mainNav: [
    {
      title: "Dashboard",
      href: "/",
    },
    {
      title: "Purchase Orders",
      href: "/orders",
    },
  ],
  links: {
    twitter: "https://twitter.com/shadcn",
    github: "https://github.com/shadcn/ui",
    docs: "https://ui.shadcn.com",
  },
}
