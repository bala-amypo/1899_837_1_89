public class InvoiceCategorizationEngine {

    public Category determineCategory(
        Invoice invoice, List<CategorizationRule> rules) {

        return rules.stream()
            .sorted((a,b) -> b.getPriority() - a.getPriority())
            .filter(rule -> matches(invoice.getDescription(), rule))
            .map(CategorizationRule::getCategory)
            .findFirst()
            .orElse(null);
    }

    private boolean matches(String desc, CategorizationRule rule) {
        if (desc == null) return false;

        return switch (rule.getMatchType()) {
            case "EXACT" ->
                desc.equalsIgnoreCase(rule.getKeyword());
            case "CONTAINS" ->
                desc.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case "REGEX" ->
                Pattern.compile(rule.getKeyword()).matcher(desc).find();
            default -> false;
        };
    }
}
